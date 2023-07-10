import React from 'react';
import {useForm} from "react-hook-form";

function EditCard(props) {
    const {control, handleSubmit, reset} = useForm({
        defaultValues: {
            cardNumber: "",
            owner_name: "",
            owner_surname: "",
            beforeDate: "",
        }
    });

    const onSubmit = data => {

    };


    return (
        <div></div>
    );
}

export default EditCard;