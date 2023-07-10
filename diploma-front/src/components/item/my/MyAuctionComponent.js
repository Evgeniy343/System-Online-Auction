import React, {useState} from 'react';
import {Button} from "react-bootstrap";
import UpdateAuction from "../../update/UpdateAuction";

function MyAuctionComponent(props) {

    const [showAuction, setAuctionShow] = useState(false);

    const auctionHandleClose = () => setAuctionShow(false)
    const auctionHandleShow = () => setAuctionShow(true)

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
            <p> <label className="auction_field"><b>Description: </b></label>{props.auction.product.description}</p>
            <b><label className="auction_field"><b>Status: </b></label>{props.auction.status}</b>
            <Button onClick={auctionHandleShow} className="auction_button" variant="primary" type="submit">
                Update
            </Button>
            <Button onClick={() => {props.delete(props.auction.id)}} className="auction_button" variant="danger" type="submit">
                Delete
            </Button>
            <UpdateAuction showModal={showAuction} closeModal={auctionHandleClose} update={props.update} auction={props.auction}/>
        </div>
    );
}

export default MyAuctionComponent;