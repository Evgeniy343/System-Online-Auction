import React from 'react';
import {Button, Form, FormCheck, FormControl, FormGroup, FormLabel, FormText, Modal} from "react-bootstrap";
import {Controller, useForm} from "react-hook-form";
import Section1 from "./Section1";
import Section2 from "./Section2";
import Section3 from "./Section3";
import Section4 from "./Section4";
import Section5 from "./Section5";

function Register(props) {

    const {control, handleSubmit, reset} = useForm({
        defaultValues: {
            name: "",
            surname: "",
            patronymic: "",
            password: "",
            photo: "",
            email: "",
            phoneNumber: "",
            role: "USER",
            registrationAddress: {
                country: "",
                region: "",
                city: "",
                street: "",
                house: "",
                flat: ""
            },
            copiesOfDocuments: {
                copyOfDocumentPageWithPhoto: "",
                copyOfDocumentPageWithPersonalNumber: "",
                copyOfPageOfDocumentResidencePermit: ""
            },
            identification: {
                identityDocument: "",
                seriesAndDocumentNumber: "",
                whoIssuedDocument: "",
                whenDocumentWasIssued: ""
            },
            requisitesForReturnOfDeposit: {
                currentAccountNumber: "",
                bankNameAndBranchNumber: "",
                bankCode: "",
                bankAddress: ""
            }
        },
    });

    const onSubmit = data => {
        props.onRegister(data)
    };


    return (
        <Modal size="lg" show={props.showModal} onHide={props.closeModal}>
            <Modal.Header closeButton>
                <Modal.Title>Sign up</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form onSubmit={handleSubmit(onSubmit)}>

                    <Section1 control={control}/>

                    <Form.Group controlId="formAvatar" className="mb-3">
                        <Form.Label>Avatar</Form.Label>
                        <Controller
                            control={control}
                            name="photo"
                            render={
                                ({field, fieldState}) => (
                                    <FormControl
                                        placeholder="Enter photo"
                                        {...field}
                                        type={"file"}
                                        multiline={false}
                                        helperText={fieldState.error?.message || " "}
                                        error={!!fieldState.error}
                                    />
                                )
                            }
                        ></Controller>
                    </Form.Group>

                    <Section2 control={control}/>

                    <FormGroup controlId="formBasicPhoneNumber">
                        <FormLabel>Phone number</FormLabel>
                        <Controller
                            control={control}
                            name="phoneNumber"
                            render={
                                ({field, fieldState}) => (
                                    <FormControl
                                        placeholder="Enter phone number"
                                        {...field}
                                        type={"text"}
                                        multiline={false}
                                        helperText={fieldState.error?.message || " "}
                                        error={!!fieldState.error}
                                    />
                                )
                            }
                        ></Controller>
                    </FormGroup>

                    <Section3 control={control}/>

                    <Section4 control={control}/>

                    <Section5 control={control}/>

                    <FormGroup controlId="formBasicEmail">
                        <FormLabel>Email Address</FormLabel>
                        <Controller
                            control={control}
                            name="email"
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
                    <Button variant="primary" type="register">
                        Submit
                    </Button>
                </Form>
            </Modal.Body>
        </Modal>
    );
}

export default Register;


// surname: "",
//     patronymic: "",
//     password: "",
//     photo: "",
//     email: "",
//     phoneNumber: "",
//     role: "ADMIN",
//     registrationAddress: {
//     country: "",
//         region: "",
//         city: "",
//         street: "",
//         house: "",
//         flat: ""
// },
// copiesOfDocuments: {
//     copyOfDocumentPageWithPhoto: "",
//         copyOfDocumentPageWithPersonalNumber: "",
//         copyOfPageOfDocumentResidencePermit: ""
// },
// identification: {
//     identityDocument: "",
//         seriesAndDocumentNumber: "",
//         whoIssuedDocument: "",
//         whenDocumentWasIssued: ""
// },
// requisitesForReturnOfDeposit: {
//     currentAccountNumber: "",
//         bankNameAndBranchNumber: "",
//         bankCode: "",
//         bankAddress: ""
// }