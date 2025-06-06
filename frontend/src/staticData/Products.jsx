
export class Product {
        constructor (id, name = null, author, date, genre, xAdded = 0) {
          this.id = id;
          this.name = name;
          this.author = author;
          this.date = date;
          this.genre = genre;
          this.xAdded = xAdded;
        }
}

export const ProductCollection = [
        new Product (1, "Partido 1", "Usuario1", "2025-05-29T10:30:00.000Z", "open", 0),
        new Product (2, "Partido 2", "Usuario4", "2022-09-20T21:45:00.000Z", "open", 0),
        new Product (3, "Partido 3", "Usuario1", "2024-12-01T08:15:00.000Z", "open", 0),
        new Product (4, "Partido 4", "Usuario3", "2025-05-29T14:30:00.000Z", "open", 0),
        new Product (5, "Partido 5", "Usuario1", "2023-07-20T21:45:00.000Z", "open", 0),
        new Product (6, "Partido 6", "Usuario2", "2025-05-29T14:45:00.000Z", "open", 0)
];

export const ProductsByGenre = ProductCollection.reduce((acc, product) => {
        if(!acc[product.genre]) {
                acc[product.genre] = [];
        }
        acc[product.genre] = [...acc[product.genre], product];
        return acc;
}, {});