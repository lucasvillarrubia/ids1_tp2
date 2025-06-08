import { FaLockOpen, FaLock, FaTrophy, FaUser } from "react-icons/fa6";
import { RiTeamFill } from "react-icons/ri";
import { TbSoccerField } from "react-icons/tb";

export const CategoryCollection = [
        {
                id: 1,
                name: 'Partidos Abiertos',
                category: 'matches',
        },
        {
                id: 2,
                name: 'Partidos Cerrados',
                category: 'closed',
        },
        {
                id: 3,
                name: 'Equipos',
                category: 'teams',
        },
        {
                id: 4,
                name: 'Torneos',
                category: 'tournament',
        },
        {
                id: 5,
                name: 'Canchas',
                category: 'fields',
        },
        {
                id: 6,
                name: 'Usuarios',
                category: 'users',
        }
]; 

export const IconByCat = {
        matches: <FaLockOpen />,
        closed: <FaLock />,
        teams: <RiTeamFill />,
        tournament: <FaTrophy />,
        fields: <TbSoccerField />,
        users: <FaUser />
      }
