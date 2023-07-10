import React, {useState} from 'react';
import {FaShoppingCart} from "react-icons/fa";
import AuctionInBasket from "./AuctionInBasket";
import SingIn from "./auth/SingIn";
import Register from "./auth/register/Register";
import CreateUser from "./create/CreateUser";
import CreateAuction from "./create/CreateAuction";
import CreateCard from "./create/CreateCard";

function Header(props) {

    const [showRegister, setRegisterShow] = useState(false);
    const [showAuthorization, setAuthorizationShow] = useState(false);
    const [showCreateUser, setCreateUserShow] = useState(false);
    const [showCreateAuction, setCreateAuctionShow] = useState(false);
    const [showCreateCard, setCreateCardShow] = useState(false);
    let [cartOpen, setCartOpen] = useState(false)
    const authorizationHandleShow = () => setAuthorizationShow(true)
    const authorizationHandleClose = () => setAuthorizationShow(false)
    const registerHandleShow = () => setRegisterShow(true)
    const registerHandleClose = () => setRegisterShow(false)

    const createUserHandleShow = () => setCreateUserShow(true)
    const createUserHandleClose = () => setCreateUserShow(false)

    const createAuctionHandleShow = () => setCreateAuctionShow(true)
    const createAuctionHandleClose = () => setCreateAuctionShow(false)

    const createCardHandleShow = () => setCreateCardShow(true)
    const createCardHandleClose = () => setCreateCardShow(false)

    const showAuctions = () => {
        return (
            <div>
                {props.auctionsInBasket.map(auction => (
                    <AuctionInBasket key={auction.id} auction={auction}
                                     deleteAuctionFromBasket={props.deleteAuctionFromBasket}
                                     onShowFullAuctionInfo={props.onShowFullAuctionInfo}/>
                ))}
            </div>
        )
    }

    const showNothing = () => {
        return (
            <div className="empty">
                <h2>Cart is empty!</h2>
            </div>
        )
    }

    const showCart = () => {
        setCartOpen(cartOpen = !cartOpen)
    }

    const goBack = () => {
        props.navigate("/")
        window.location.reload()
    }


    return (
        <header>
            <div>
                <span className="logo">Zhendos Auction</span>
                <ul className='nav'>
                    <li>
                        <input type="search" placeholder="Search..."/>
                    </li>

                    {props.showCreateCard && <li onClick={createCardHandleShow}><b>Create Card</b></li>}
                    {props.showCreateAuction && <li onClick={createAuctionHandleShow}><b>Create Auction</b></li>}
                    {props.showCreateUser && <li onClick={createUserHandleShow}><b>Create User</b></li>}

                    {props.showCards && <li><b>My Cards</b></li>}
                    {props.showBids && <li onClick={props.getMyBids}><b>My Bids</b></li>}
                    {props.showUsers && <li onClick={props.getAllUsers}><b>Users</b></li>}
                    {props.showMyAuctions && <li onClick={props.getMyAuctions}><b>My Auctions</b></li>}

                    <li onClick={goBack}><b>All Auctions</b></li>

                    {props.showSignOut && <li onClick={props.signOut}><b>Sign out</b></li>}
                    {props.showSignUp && <li onClick={registerHandleShow}><b>Sign up</b></li>}
                    {props.showSignIn && <li onClick={authorizationHandleShow}><b>Sign in</b></li>}

                </ul>
                {props.showbasket && <FaShoppingCart onClick={showCart}
                                                     className={`shop-cart-button ${cartOpen && 'active'}`}/>}
                {cartOpen && (
                    <div className="shop-cart">
                        {props.auctionsInBasket.length > 0 ?
                            showAuctions(props) : showNothing()}
                    </div>
                )}
            </div>
            <div className="presentation"/>

            <SingIn showModal={showAuthorization} closeModal={authorizationHandleClose} onSignIn={props.onSignIn}/>
            <Register showModal={showRegister} closeModal={registerHandleClose} onRegister={props.onSignUp}/>
            <CreateUser showModal={showCreateUser} closeModal={createUserHandleClose} onCreateUser={props.createUser}
                        navigate={props.navigate}/>
            <CreateCard showModal={showCreateCard} closeModal={createCardHandleClose} onCreateCard={props.createCard}
                        navigate={props.navigate}/>
            <CreateAuction showModal={showCreateAuction} closeModal={createAuctionHandleClose}
                           onCreateAuction={props.createAuction} navigate={props.navigate}/>
        </header>
    );


}

export default Header;