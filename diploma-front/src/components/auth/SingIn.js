import React, {useState} from 'react';
import {Button, Form, FormCheck, FormControl, FormGroup, FormLabel, FormText, Modal} from "react-bootstrap";
import {Controller, useForm} from "react-hook-form";

function SingIn(props) {

    const {control, handleSubmit, reset} = useForm({
        defaultValues: {
            userName: "",
            password: "",
        },
    });

    const onSubmit = data => {
        props.onSignIn(data)
    };

    return (
        <Modal centered size="lg" show={props.showModal} onHide={props.closeModal}>
            <Modal.Header closeButton>
                <Modal.Title>Sign in</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form onSubmit={handleSubmit(onSubmit)}>
                    <FormGroup controlId="formBasicEmail">
                        <FormLabel>Email Address</FormLabel>
                        <Controller
                            control={control}
                            name="userName"
                            render={
                                ({field, fieldState}) => (
                                    <FormControl
                                        placeholder="Enter email"
                                        {...field}
                                        type={"email"}
                                        multiline={false}
                                        helperText={fieldState.error?.message || " "}
                                        error={!!fieldState.error}
                                    />
                                )
                            }
                        ></Controller>
                        <FormText className="text-muted">We'll never share your email with anyone else</FormText>
                    </FormGroup>
                    <FormGroup controlId="formBasicPassword">
                        <FormLabel>Password</FormLabel>
                        <Controller
                            control={control}
                            name="password"
                            render={
                                ({field, fieldState}) => (
                                    <FormControl
                                        placeholder="Enter password"
                                        {...field}
                                        type={"password"}
                                        multiline={false}
                                        helperText={fieldState.error?.message || " "}
                                        error={!!fieldState.error}
                                    />
                                )
                            }
                        ></Controller>
                    </FormGroup>
                    <FormGroup controlId="formBasicCheckBox">
                        <FormCheck type="checkbox" label="Remember me"/>
                    </FormGroup>
                    <Button variant="primary" type="submit">
                        Submit
                    </Button>
                </Form>
            </Modal.Body>
        </Modal>
    );
}

export default SingIn;