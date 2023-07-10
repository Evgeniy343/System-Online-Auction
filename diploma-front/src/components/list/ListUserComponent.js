import React from 'react';
import MyAuctionComponent from "../item/my/MyAuctionComponent";
import UserComponent from "../item/UserComponent";

function ListUserComponent(props) {
    return (
        <main>
            {props.users.map(user => (
                    <UserComponent onShowFullAuctionInfo={props.onShowFullAuctionInfo} key={user.id}
                                        user={user} delete={props.delete} update={props.update}/>
                )
            )}
        </main>
    );
}

export default ListUserComponent;