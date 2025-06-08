import React from 'react'
import { ItemCardUI, ItemTitle, ItemInfo, ItemAuthor, ItemPrice, ItemButton } from './ItemStyles.js';
import { useDispatch } from 'react-redux';
import {formatDate} from "../../utils/formatDate.js";

const ItemCard = (item) => {
        if (!item || typeof item !== 'object') return null;

        return (
                <ItemCardUI>
                        <ItemInfo>
                                {Object.entries(item).map(([key, value]) => (
                                    <ItemAuthor key={key}>
                                            {key}: {typeof value === 'object' ? JSON.stringify(value) : String(value)}
                                    </ItemAuthor>
                                ))}
                        </ItemInfo>
                </ItemCardUI>
        )
}

export default ItemCard


// const ItemCard = (item) => {
//         const dispatch = useDispatch();
//         return (
//             <ItemCardUI>
//                     <ItemInfo>
//                             <ItemAuthor>
//                                     #{item.id}
//                             </ItemAuthor>
//                             <ItemTitle>
//                                     {item.name}
//                             </ItemTitle>
//                             <ItemPrice>
//                                     Creado el {formatDate(item.date)}
//                             </ItemPrice>
//                     </ItemInfo>
//                     {/* <ItemButton onClick={() => dispatch(addToCart(item))}>
//                                 AGREGAR AL CARRITOP
//                         </ItemButton> */}
//             </ItemCardUI>
//         )
// }
//
// export default ItemCard