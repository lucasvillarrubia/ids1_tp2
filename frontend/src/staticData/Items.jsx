export class Item {
    constructor(data, index) {
        Object.assign(this, data);
        this.renderIndex = index;
    }
}