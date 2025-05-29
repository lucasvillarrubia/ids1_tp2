
class product {
        constructor (id, name, author, price, genre, xAdded, xOvertimeAdded, isAVinyl) {
          this.id = id;
          this.name = name;
          this.author = author;
          this.price = price;
          this.genre = genre;
          this.xAdded = xAdded;
          this.xOvertimeAdded = xOvertimeAdded;
          this.isAVinyl = isAVinyl;
        }
}

export const ProductCollection = [
        new product (1, "After Hours", "The Weeknd", 9500, "open", 0, 0, true),
        new product (2, "Gemini Rights", "Steve Lacy", 6700, "open", 0, 0, true),
        new product (3, "Happier Than Ever", "Billie Eilish", 8600, "open", 0, 0, true),
        new product (4, "Mr. Morale & the Big Steppers", "Kendrick Lamar", 10000, "open", 0, 0, true),
        new product (5, "Superache", "Conan Gray", 9900, "open", 0, 0, true),
        new product (6, "Harry's House", "Harry Styles", 21650, "open", 0, 0, true)
];

export const ProductsByGenre = ProductCollection.reduce((acc, product) => {
        if(!acc[product.genre]) {
                acc[product.genre] = [];
        }
        acc[product.genre] = [...acc[product.genre], product];
        return acc;
}, {});