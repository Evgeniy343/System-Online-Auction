import React from "react"
import "bootstrap/dist/css/bootstrap.min.css"
import AuctionComponent from "../item/AuctionComponent";

const ListAuctionComponent = (props) => {

    return (
        <main>
            {props.auctions.map(auction => (
                    <AuctionComponent onShowFullAuctionInfo={props.onShowFullAuctionInfo} key={auction.id}
                                      auction={auction} onAdd={props.onAdd}/>
                )
            )}
        </main>
    )
}

export default ListAuctionComponent