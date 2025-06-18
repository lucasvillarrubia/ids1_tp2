import React, { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import axios from 'axios';

const CategoryResultsSection = () => {
  const selected = useSelector(state => state.categories.selectedCategory);
  const [results, setResults] = useState([]);

  useEffect(() => {
    if (!selected) return;

    // Example mapping: category string → endpoint
    const endpoints = {
      'Partidos Abiertos': '/api/matches/availableMatches',
      'Partidos Cerrados': '/api/matches/close',
      //'Torneos': '/api/tournaments',
      // Add more as needed
    };

    const endpoint = endpoints[selected.name];
    if (!endpoint) return;

    axios.get(endpoint)
      .then(res => setResults(res.data))
      .catch(err => console.error("Error loading category data:", err));
  }, [selected]);

  return (
    <div>
      <h2>{selected?.name}</h2>
      <p>
      Esto
      es un
      párrafo
      </p>
    </div>
  );
};

export default CategoryResultsSection;
