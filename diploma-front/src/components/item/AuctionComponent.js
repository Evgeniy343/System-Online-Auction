import React, {useState} from 'react';
import StripeButton from "../payment/StripeButton";
import CreateBid from "../create/CreateBid";
import {Button} from "react-bootstrap";

function AuctionComponent(props) {

    const [showParticipation, setParticipationShow] = useState(false);

    const participationHandleClose = () => setParticipationShow(false)
    const participationHandleShow = () => setParticipationShow(true)
    //
    // function price () {
    //     var price = props.auction.fullPaymentTerm.substring(0,props.auction.fullPaymentTerm.length - 1)
    //     return price
    // }

    return (
        <div className="item">
            <img src={"./img/product/" + props.auction.product.photos[0].name} alt=""
                 onClick={() => props.onShowFullAuctionInfo(props.auction)}/>
            <h2>{props.auction.product.name}</h2>
            <b><label className="auction_field"><b>Initial rate: </b></label>{props.auction.initialRate}</b>
            <p/>
            <b><label className="auction_field"><b>Full payment term: </b></label>{props.auction.fullPaymentTerm}</b>
            <p/>
            <b><label className="auction_field"><b>Trading start time: </b></label>{props.auction.tradingStartTime}</b>
            <p/>
            <b><label className="auction_field"><b>Trading close time: </b></label>{props.auction.tradingCloseTime}</b>
            <p><label className="auction_field"><b>Description: </b></label>{props.auction.product.description}</p>
            <b><label className="auction_field"><b>Status: </b></label>{props.auction.status}</b>
            {props.showAddToBasket &&
                <div className="add-to-cart" onClick={() => props.addToBasket(props.auction.id)}>+</div>}
            {props.showPay && <StripeButton className="auction_button" price={props.auction.fullPaymentTerm.substring(0,props.auction.fullPaymentTerm.length - 1)}/>}
            {props.showPay && <Button className="auction_button" onClick={participationHandleShow} variant="warning">
                Create Bid</Button>}

            <CreateBid showModal={showParticipation} closeModal={participationHandleClose} createBid={props.createBid}
                       auction={props.auction}/>
        </div>
    );
}

export default AuctionComponent;