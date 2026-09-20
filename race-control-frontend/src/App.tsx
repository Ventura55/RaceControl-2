import { useState, useEffect } from 'react';
import { raceControlApi, type StandingsResponse } from './core/services/api';
import { Login } from './components/Login'; // 👈 1. Importamos el componente de Login
import './App.css';

export default function App() {
  // 🔑 2. Estado de autenticación (recupera el token guardado en el navegador)
  const [token, setToken] = useState<string | null>(() => localStorage.getItem('token'));

  const [standings, setStandings] = useState<StandingsResponse[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [aiReport, setAiReport] = useState<string>('');
  const [loadingAi, setLoadingAi] = useState<boolean>(false);

  // Estado del formulario de sanción
  const [raceId, setRaceId] = useState<number>(1);
  const [driverNumber, setDriverNumber] = useState<number>(14);
  const [reason, setReason] = useState<string>('');
  const [seconds, setSeconds] = useState<number>(5);

  // Solo carga la clasificación si hay un token activo
  useEffect(() => {
    if (token) {
      loadStandings();
    }
  }, [token]);

  const loadStandings = async () => {
    try {
      setLoading(true);
      const data = await raceControlApi.getStandings();
      setStandings(data);
    } catch (err) {
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  const handleApplyPenalty = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      await raceControlApi.createPenalty(raceId, driverNumber, reason, seconds);
      alert('⚡ Sanción aplicada correctamente');
      setReason('');
      loadStandings();
    } catch (err) {
      alert('❌ Error al aplicar sanción: ' + (err as Error).message);
    }
  };

  const handleGetInsights = async () => {
    try {
      setLoadingAi(true);
      const report = await raceControlApi.getAiInsights('GP de Mónaco');
      setAiReport(report);
    } catch (err) {
      setAiReport('Error al obtener informe de la IA.');
    } finally {
      setLoadingAi(false);
    }
  };

  // 🚪 3. Función para cerrar sesión
  const handleLogout = () => {
    localStorage.removeItem('token');
    setToken(null);
  };

  // 🔴 4. Si NO está autenticado, muestra únicamente la pantalla de Login
  if (!token) {
    return <Login onLoginSuccess={(newToken) => setToken(newToken)} />;
  }

  // 🟢 5. Si SÍ está autenticado, muestra el Dashboard completo de la F1
  return (
      <div className="fia-dashboard">
        <header className="fia-header">
          <div className="logo-badge">FIA</div>
          <h1>RACE CONTROL CENTER</h1>

          {/* Botón de Logout añadido en la cabecera */}
          <button onClick={handleLogout} className="btn btn-danger" style={{ marginLeft: 'auto' }}>
            CERRAR SESIÓN
          </button>
        </header>

        <div className="dashboard-grid">
          {/* Tabla de Clasificación */}
          <section className="card standings-card">
            <h2>🏎️ Clasificación Mundial de Pilotos</h2>
            {loading ? (
                <p>Cargando telemetría...</p>
            ) : (
                <table className="fia-table">
                  <thead>
                  <tr>
                    <th>Pos</th>
                    <th>Piloto</th>
                    <th>Escudería</th>
                    <th>Puntos</th>
                  </tr>
                  </thead>
                  <tbody>
                  {standings.map((item, index) => (
                      <tr key={index}>
                        <td className="pos-cell">{index + 1}</td>
                        <td className="driver-name">{item.driver?.name || `Piloto #${item.raceResult?.idDriver}`}</td>
                        <td>{item.team?.name || 'N/A'}</td>
                        <td className="points-cell">{item.raceResult?.points} PTS</td>
                      </tr>
                  ))}
                  </tbody>
                </table>
            )}
          </section>

          {/* Formulario de Sanciones */}
          <section className="card penalty-card">
            <h2>🚩 Panel de Comisarios (Sanciones)</h2>
            <form onSubmit={handleApplyPenalty} className="fia-form">
              <div className="form-group">
                <label>ID Carrera:</label>
                <input type="number" value={raceId} onChange={(e) => setRaceId(Number(e.target.value))} required />
              </div>
              <div className="form-group">
                <label>Nº Piloto:</label>
                <input type="number" value={driverNumber} onChange={(e) => setDriverNumber(Number(e.target.value))} required />
              </div>
              <div className="form-group">
                <label>Segundos Penalización:</label>
                <input type="number" value={seconds} onChange={(e) => setSeconds(Number(e.target.value))} required />
              </div>
              <div className="form-group">
                <label>Motivo de la Sanción:</label>
                <textarea value={reason} onChange={(e) => setReason(e.target.value)} placeholder="Ej: Superar límites de pista" required />
              </div>
              <button type="submit" className="btn btn-danger">APLICAR SANCIÓN</button>
            </form>
          </section>
        </div>

        {/* IA Insights */}
        <section className="card ai-card">
          <div className="ai-header">
            <h2>🤖 FIA AI Telemetry Insights (Gemini)</h2>
            <button onClick={handleGetInsights} className="btn btn-ai" disabled={loadingAi}>
              {loadingAi ? 'Analizando...' : 'Generar Informe IA'}
            </button>
          </div>
          {aiReport && <pre className="ai-output">{aiReport}</pre>}
        </section>
      </div>
  );
}