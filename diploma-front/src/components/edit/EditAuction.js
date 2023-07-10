import React from 'react';
import {useForm} from "react-hook-form";

function EditAuction(props) {
    const {control, handleSubmit, reset} = useForm({
        defaultValues: {
            initialRate: "",
            depositAmount: "20%",
            fullPaymentTerm: "",
            tradingStartTime: "",
            tradingCloseTime: "",
            product: {
                name: "",
                category: "",
                description: "",
                photos: [
                    {
                        name: ""
                    }
                ]
            },
            status: "ACTIVE"
        },
    });

    const onSubmit = data => {

    };

    return (
        <div>

        </div>
    );
}

export default EditAuction;