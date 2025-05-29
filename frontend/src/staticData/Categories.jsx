import { FaLockOpen, FaLock, FaTrophy, FaUser } from "react-icons/fa6";
import { RiTeamFill } from "react-icons/ri";
import { TbSoccerField } from "react-icons/tb";

export const CategoryCollection = [
        {
                id: 1,
                name: 'Partidos Abiertos',
                category: 'open',
        },
        {
                id: 2,
                name: 'Partidos Cerrados',
                category: 'closed',
        },
        {
                id: 3,
                name: 'Equipos',
                category: 'team',
        },
        {
                id: 4,
                name: 'Torneos',
                category: 'tournament',
        },
        {
                id: 5,
                name: 'Canchas',
                category: 'field',
        },
        {
                id: 6,
                name: 'Usuarios',
                category: 'user',
        }
]; 

export const IconByCat = {
        open: <FaLockOpen />,
        closed: <FaLock />,
        team: <RiTeamFill />,
        tournament: <FaTrophy />,
        field: <TbSoccerField />,
        user: <FaUser />
      }
