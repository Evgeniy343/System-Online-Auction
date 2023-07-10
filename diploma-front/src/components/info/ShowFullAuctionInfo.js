import React from 'react';

function ShowFullAuctionInfo(props) {
    return (
        <div className="full-item">
            <div>
                <img src={"./img/product/" + props.fullAuctionInfo.product.photos[0].name} alt=""
                     onClick={() => props.onShowFullAuctionInfo(props.auction)}/>
                <h2>{props.fullAuctionInfo.product.name}</h2>
                <b><label className="auction_field"><b>Initial rate: </b></label>{props.fullAuctionInfo.initialRate}</b>
                <p/>
                <b><label className="auction_field"><b>Full payment term: </b></label>{props.fullAuctionInfo.fullPaymentTerm}</b>
                <p/>
                <b><label className="auction_field"><b>Trading start time: </b></label>{props.fullAuctionInfo.tradingStartTime}</b>
                <p/>
                <b><label className="auction_field"><b>Trading close time: </b></label>{props.fullAuctionInfo.tradingCloseTime}</b>
                <p> <label className="auction_field"><b>Description: </b></label>{props.fullAuctionInfo.product.description}</p>
                <b><label className="auction_field"><b>Status: </b></label>{props.fullAuctionInfo.status}</b>
                <div className="add-to-cart" onClick={() => props.addToBasket(props.fullAuctionInfo.id)}>+</div>
            </div>
        </div>
    );
}

export default ShowFullAuctionInfo;