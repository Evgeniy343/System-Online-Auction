import React from 'react';
import AuctionComponent from "../../item/AuctionComponent";
import MyAuctionComponent from "../../item/my/MyAuctionComponent";

function ListMyAuctionsComponent(props) {
    return (
        <main>
            {props.auctions.map(auction => (
                    <MyAuctionComponent onShowFullAuctionInfo={props.onShowFullAuctionInfo} key={auction.id}
                                      auction={auction} delete={props.delete} update={props.update}/>
                )
            )}
        </main>
    )
}

export default ListMyAuctionsComponent;