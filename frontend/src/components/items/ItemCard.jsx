import React from 'react';
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import {
  ItemCardUI,
  ItemTitle,
  ItemAuthor,
  ItemInfo,
  ItemPrice
} from './ItemStyles.js';
import { formatDate } from '../../utils/formatDate.js';

const zoneMap = {
  // Zona norte
  VICENTE_LOPEZ: "Vicente López",
  SAN_ISIDRO: "San Isidro",
  SAN_FERNANDO: "San Fernando",
  TIGRE: "Tigre",
  ESCOBAR: "Escobar",
  PILAR: "Pilar",
  JOSE_C_PAZ: "José C. Paz",
  MALVINAS_ARGENTINAS: "Malvinas Argentinas",
  SAN_MIGUEL: "San Miguel",
  SAN_MARTIN: "San Martín",

  // Zona oeste
  LA_MATANZA: "La Matanza",
  TRES_DE_FEBRERO: "Tres de Febrero",
  HURLINGHAM: "Hurlingham",
  MORON: "Morón",
  ITUZAINGO: "Ituzaingó",
  MERLO: "Merlo",
  MORENO: "Moreno",
  GENERAL_RODRIGUEZ: "General Rodríguez",
  MARCOS_PAZ: "Marcos Paz",

  // Zona sur
  AVELLANEDA: "Avellaneda",
  LANUS: "Lanús",
  LOMAS_DE_ZAMORA: "Lomas de Zamora",
  ESTEBAN_DE_ECHEVERRIA: "Esteban Echeverría",
  EZEIZA: "Ezeiza",
  SAN_VICENTE: "San Vicente",
  PRESIDENTE_PERON: "Presidente Perón",
  ALMIRANTE_BROWN: "Almirante Brown",
  QUILMES: "Quilmes",
  BERAZATEGUI: "Berazategui",
  FLORENCIO_VARELA: "Florencio Varela",

  // CABA
  AGRONOMIA: "Agronomía",
  ALMAGRO: "Almagro",
  BALVANERA: "Balvanera",
  BARRACAS: "Barracas",
  BELGRANO: "Belgrano",
  BOEDO: "Boedo",
  CABALLITO: "Caballito",
  CHACARITA: "Chacarita",
  COGHLAN: "Coghlan",
  COLEGIALES: "Colegiales",
  CONSTITUCION: "Constitución",
  FLORES: "Flores",
  FLORESTA: "Floresta",
  LA_BOCA: "La Boca",
  LA_PATERNAL: "La Paternal",
  LINIERS: "Liniers",
  MATADEROS: "Mataderos",
  MONTE_CASTRO: "Monte Castro",
  MONSERRAT: "Monserrat",
  NUEVA_POMPEYA: "Nueva Pompeya",
  NUNEZ: "Núñez",
  PALERMO: "Palermo",
  PARQUE_AVELLANEDA: "Parque Avellaneda",
  PARQUE_CHACABUCO: "Parque Chacabuco",
  PARQUE_CHAS: "Parque Chas",
  PARQUE_PATRICIOS: "Parque Patricios",
  PUERTO_MADERO: "Puerto Madero",
  RECOLETA: "Recoleta",
  RETIRO: "Retiro",
  SAAVEDRA: "Saavedra",
  SAN_CRISTOBAL: "San Cristóbal",
  SAN_NICOLAS: "San Nicolás",
  SAN_TELMO: "San Telmo",
  VELEZ_SARSFIELD: "Vélez Sarsfield",
  VERSALLES: "Versalles",
  VILLA_CRESPO: "Villa Crespo",
  VILLA_DEL_PARQUE: "Villa del Parque",
  VILLA_DEVOTO: "Villa Devoto",
  VILLA_GENERAL_MITRE: "Villa Gral. Mitre"
};

const featureMap = {
  GRASS: "Pasto natural",
  CEMENT: "Cemento",
  ARTIFICIAL_TURF: "Césped sintético",
  ROOF: "Techo",
  LIGHTS: "Iluminación",
  RESTROOMS: "Baños",
  PARKING: "Estacionamiento",
  WIFI: "Wi-Fi",
  FOOD: "Comida",
  DRINK: "Bebidas",
  MUSIC: "Música"
};


const ItemCard = ({ id, name, ownerEmail, location, zone, price, features, schedule, description, date, organizer, participationTypeDTO, timeRange, state}) => {
  const renderSchedule = () => {
    if (!Array.isArray(schedule) || schedule.length === 0) return null;
    return (
      <ItemCardUI>
      <ItemInfo>
        Horario:
        {schedule.map((slot, i) => (
          <div key={i}>
            Día(s): {slot.days?.join(', ') || 'N/A'}<br />
            Desde: {slot.startHour} {slot.endHour ? `- Hasta: ${slot.endHour}` : ''}
          </div>
        ))}
      </ItemInfo>
      </ItemCardUI>
    );
  };

  // itemIdentifier is the id (if exists), otherwise the name
  const itemIdentifier = id || name || organizer || 'Sin Identificador';
  const encodedKey = encodeURIComponent(itemIdentifier)
  const navigate = useNavigate();
  const itemCategory = useSelector(state => state.categories.selectedCategory);

  return (
    <ItemCardUI onClick={() => navigate(`/${itemCategory}/${encodedKey}`)}>
      <ItemInfo>
        <ItemAuthor>#{itemIdentifier}</ItemAuthor>

        <ItemTitle>{name || organizer}</ItemTitle>

        {location && <ItemAuthor>Ubicación: {location}</ItemAuthor>}
        {participationTypeDTO && <ItemAuthor>Cantidad de jugadores actuales: {participationTypeDTO.players.length}</ItemAuthor>}
        {participationTypeDTO && <ItemAuthor>Cantidad de jugadores mínima: {participationTypeDTO.minPlayersCount}</ItemAuthor>}
        {participationTypeDTO && <ItemAuthor>Cantidad de jugadores máxima: {participationTypeDTO.maxPlayersCount}</ItemAuthor>}
        {timeRange && <ItemAuthor>Inicio: {timeRange.start}</ItemAuthor>}
        {timeRange && <ItemAuthor>Fin: {timeRange.end}</ItemAuthor>}
        {zone && <ItemAuthor>Zona: {zoneMap[zone]}</ItemAuthor>}
        {price && <ItemAuthor>Precio: ${price}</ItemAuthor>}
        {state && <ItemAuthor>Estado: {state}</ItemAuthor>}
        {features?.length > 0 && (
          <ItemAuthor>
            Características: {features.map(f => featureMap[f] || f).join(', ')}
          </ItemAuthor>
        )}
        {description && <ItemAuthor>Descripción: {description}</ItemAuthor>}
        {renderSchedule()}
        {date && <ItemPrice>Creado el {formatDate(date)}</ItemPrice>}
      </ItemInfo>
    </ItemCardUI>
  );
};

export default ItemCard;
