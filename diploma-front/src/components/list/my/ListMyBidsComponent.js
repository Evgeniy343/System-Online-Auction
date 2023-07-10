import React from 'react';
import MyAuctionComponent from "../../item/my/MyAuctionComponent";
import MyBidComponent from "../../item/my/MyBidComponent";

function ListMyBidsComponent(props) {
    return (
        <main>
            {props.bids.map(bid => (
                    <MyBidComponent onShowFullAuctionInfo={props.onShowFullAuctionInfo} key={bid.id}
                                        bid={bid} delete={props.delete} update={props.update}/>
                )
            )}
        </main>
    );
}

export default ListMyBidsComponent;