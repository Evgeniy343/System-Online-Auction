import React, {useState} from 'react';
import {FaShoppingCart} from "react-icons/fa";
import AuctionInBucket from "./AuctionInBucket";
import Register from "./auth/register/Register";
import SingIn from "./auth/SingIn";

const showAuctions = (props) => {
    return (
        <div>
            {props.auctions.map(auction => (
                <AuctionInBucket key={auction.id} auction={auction}/>
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

function Header(props) {

    const [showRegister, setRegisterShow] = useState(false);
    const [showAuthorization, setAuthorizationShow] = useState(false);
    let [cartOpen, setCartOpen] = useState(false)
    // const [showParticipation, setParticipationShow] = useState(false);

    // const participationHandleClose = () => setParticipationShow(false)
    // const participationHandleShow = () => setParticipationShow(true)
    const authorizationHandleShow = () => setAuthorizationShow(true)
    const authorizationHandleClose = () => setAuthorizationShow(false)
    const registerHandleShow = () => setRegisterShow(true)
    const registerHandleClose = () => setRegisterShow(false)

    const showMyCards = () => {
        if (localStorage.getItem("jwt_token") === null) {
            setAuthorizationShow(true)
        }
    }

    const showMyAuctions = () => {
        if (localStorage.getItem("jwt_token") === null) {
            setAuthorizationShow(true)
        }
    }

    const showMyBids = () => {
        if (localStorage.getItem("jwt_token") === null) {
            setAuthorizationShow(true)
        }
    }

    const showBasket = () => {
        if (localStorage.getItem("jwt_token") === null) {
            setAuthorizationShow(true)
        } else {
            setCartOpen(cartOpen = !cartOpen)
        }
    }


    return (
        <header>
            <div>
                <span className="logo">Zhendos Auction</span>
                <ul className='nav'>
                    <li>
                        <input type="search" placeholder="Search..."/>
                    </li>
                    <li onClick={showMyCards}><b>Cards</b></li>
                    <li onClick={showMyBids}><b>Bids</b></li>
                    <li onClick={showMyAuctions}><b>Auctions</b></li>
                    <li onClick={registerHandleShow}><b>Sign up</b></li>
                    <li onClick={authorizationHandleShow}><b>Sign in</b></li>
                </ul>
                <FaShoppingCart onClick={showBasket}
                                className={`shop-cart-button ${cartOpen && 'active'}`}/>
                {cartOpen && (
                    <div className="shop-cart">
                        {props.auctions.length > 0 ?
                            showAuctions(props) : showNothing()}
                    </div>
                )}
            </div>
            <div className="presentation"/>

            {/*<Modal centered size="lg" show={showParticipation} onHide={participationHandleClose}>*/}
            {/*    <Modal.Header closeButton>*/}
            {/*        <Modal.Title>Participation in the auction</Modal.Title>*/}
            {/*    </Modal.Header>*/}
            {/*    <Modal.Body>*/}
            {/*        <Form>*/}
            {/*            <Form.Group as={Row} className="mb-3" controlId="formPlaintextAuctionId">*/}
            {/*                <Form.Label column sm="2">*/}
            {/*                    Auction id*/}
            {/*                </Form.Label>*/}
            {/*                <Col sm="10">*/}
            {/*                    <Form.Control plaintext readOnly defaultValue="3435346346"/>*/}
            {/*                </Col>*/}
            {/*            </Form.Group>*/}
            {/*            <FormGroup controlId="formBasicPrice">*/}
            {/*                <FormLabel>Proposed price</FormLabel>*/}
            {/*                <FormControl type="text" placeholder="Enter proposed price"/>*/}
            {/*            </FormGroup>*/}
            {/*            <FormGroup controlId="formBasicCheckBox">*/}
            {/*                <FormCheck type="checkbox"*/}
            {/*                           label="I agree with the terms and conditions of participation in the auction"/>*/}
            {/*            </FormGroup>*/}
            {/*            <Button variant="primary" type="submit">*/}
            {/*                Submit*/}
            {/*            </Button>*/}
            {/*            <Button variant="danger" type="cancel">*/}
            {/*                Cancel*/}
            {/*            </Button>*/}
            {/*        </Form>*/}
            {/*    </Modal.Body>*/}
            {/*</Modal>*/}

            <SingIn showModal={showAuthorization} closeModal={authorizationHandleClose} onSignIn={props.onSignIn}/>
            <Register showModal={showRegister} closeModal={registerHandleClose} onRegister={props.onSignUp}/>
        </header>
    );


}

export default Header;