const BASE_URL = '';

export interface StandingsResponse {
    driver: { id: number; name: string };
    team: { id: number; name: string };
    raceResult: {
        idDriver?: number;
        points: number;
        finalPosition: number;
    };
}

// 🔑 Obtiene dinámicamente la cabecera Authorization si el token existe en localStorage
const getAuthHeaders = (): Record<string, string> => {
    const token = localStorage.getItem('token');
    return token ? { 'Authorization': `Bearer ${token}` } : {};
};

export const raceControlApi = {
    // 1. Autenticación (Obtener Token)
    login: async (username: string, password: string): Promise<{ token: string }> => {
        const res = await fetch(`${BASE_URL}/api/auth/login`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username, password }),
        });

        if (!res.ok) {
            const errorData = await res.json().catch(() => ({}));
            throw new Error(errorData.error || 'Credenciales incorrectas');
        }

        return res.json();
    },

    // 2. Obtener Clasificación Mundial
    getStandings: async (): Promise<StandingsResponse[]> => {
        const res = await fetch(`${BASE_URL}/get/result/drivers`, {
            headers: {
                ...getAuthHeaders()
            }
        });
        if (!res.ok) throw new Error('Error al cargar la clasificación');
        return res.json();
    },

    // 3. Generar Sanción
    createPenalty: async (raceId: number, driverNumber: number, reason: string, secondsPenalty: number) => {
        const res = await fetch(`${BASE_URL}/penalty/${raceId}/results/${driverNumber}/penalties`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                ...getAuthHeaders()
            },
            body: JSON.stringify({ reason, secondsPenalty }),
        });
        if (!res.ok) throw new Error('No se pudo aplicar la sanción');
        return res;
    },

    // 4. Consultar IA Insights
    getAiInsights: async (raceName: string): Promise<string> => {
        const res = await fetch(`${BASE_URL}/api/races/${encodeURIComponent(raceName)}/insights`, {
            headers: {
                ...getAuthHeaders()
            }
        });
        if (!res.ok) throw new Error('Error al conectar con la IA de la FIA');
        return res.text();
    }
};