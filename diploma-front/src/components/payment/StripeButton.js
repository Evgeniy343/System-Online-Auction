import React from "react";
import axios from "axios";
import StripeCheckout from "react-stripe-checkout";

const StripeButton = ({price}) => {
    const PUBLISH_ABLE_KEY = "pk_test_51MqBt7Cl0EwdlG8RFKCEIymoAuBPlA4TLRNXwo1UPIR54DN9k937k2P4CTGtNFklBmNGkXDHJmb1sX1tOPtWUXyF00kuOSiq8s"
    const stripePrice = price * 100
    const PAYMENT_URL = "http://localhost:8765/card-service/payment"

    const onToken = (token) => {
        var jwt = localStorage.getItem("jwt_token");
        axios
            .post(PAYMENT_URL, {
                amount: stripePrice,
                token,
            }, {headers: {Authorization: `Bearer ${jwt}`}})
            .then((response) => {
                alert("Payment success!")
            })
            .catch((error) => {
                console.log(error)
                alert("Payment failed!")
            })
    }

    return(
        <StripeCheckout
            amount={stripePrice}
            label="Pay now"
            image="https://svgshare.com/i/CUz.svg"
            description={`Your total is ${price}`}
            panelLabel="Pay now"
            token={onToken}
            stripeKey={PUBLISH_ABLE_KEY}
            currency="USD"
        />
    )
}

export default StripeButton;