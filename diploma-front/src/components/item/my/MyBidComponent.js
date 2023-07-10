import React, {useState} from 'react';
import StripeButton from "../../payment/StripeButton";
import {Button} from "react-bootstrap";
import CreateBid from "../../create/CreateBid";
import UpdateBid from "../../update/UpdateBid";

function MyBidComponent(props) {

    const [showBid, setBidShow] = useState(false);

    const bidHandleClose = () => setBidShow(false)
    const bidHandleShow = () => setBidShow(true)

    return (
        <div className="item">
            <img src={"./img/product/" + props.bid.auction.product.photos[0].name} alt=""
                 onClick={() => props.onShowFullAuctionInfo(props.bid.auction)}/>
            <h2>{props.bid.auction.product.name}</h2>
            <b><label className="auction_field"><b>Initial rate: </b></label>{props.bid.auction.initialRate}</b>
            <p/>
            <b><label className="auction_field"><b>Full payment term: </b></label>{props.bid.auction.fullPaymentTerm}</b>
            <p/>
            <b><label className="auction_field"><b>Trading start time: </b></label>{props.bid.auction.tradingStartTime}</b>
            <p/>
            <b><label className="auction_field"><b>Trading close time: </b></label>{props.bid.auction.tradingCloseTime}</b>
            <b><label className="auction_field"><b>Description: </b></label>{props.bid.auction.product.description}</b>
            <b><label className="auction_field"><b>Suggested price: </b></label>{props.bid.suggestedPrice}</b>
            <Button onClick={bidHandleShow} className="auction_button" variant="primary" type="submit">
                Update
            </Button>
            <Button onClick={() => {props.delete(props.bid.id)}} className="auction_button" variant="danger" type="submit">
                Delete
            </Button>
            <UpdateBid showModal={showBid} closeModal={bidHandleClose} update={props.update} bid={props.bid}/>
        </div>
    );
}

export default MyBidComponent;