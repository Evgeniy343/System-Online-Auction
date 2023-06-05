import React from 'react';
import {FaTrash} from "react-icons/fa";

function AuctionInBucket(props) {
    return (
        <div className="item">
            <img src={"./img/product/" + props.auction.product.photos[0].name} alt=""/>
            <h2>{props.auction.product.name}</h2>
            <p>
                <b><label className="auction_field"><b>Initial rate: </b></label>{props.auction.initialRate}</b>
            </p>
            <p>
                <b><label className="auction_field"><b>Full payment term: </b></label>{props.auction.fullPaymentTerm}</b>
            </p>
            <FaTrash className="delete-icon" onClick={() => props.onDelete(props.auction.id)}/>
        </div>
    );
}

export default AuctionInBucket;