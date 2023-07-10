import React from 'react';
import {Controller, useForm} from "react-hook-form";
import {Button, Col, Container, Form, FormControl, FormGroup, FormLabel, Modal, Row} from "react-bootstrap";
import SectionAuction1 from "./SectionAuction1";
import SectionAuction2 from "./SectionAuction2";
import SectionAuction3 from "./SectionAuction3";

function CreateCard(props) {
    const {control, handleSubmit, reset} = useForm({
        defaultValues: {
            cardNumber: "",
            owner_name: "",
            owner_surname: "",
            beforeDate: "",
        }
    });

    const onSubmit = data => {
        console.log("Card data - " + data)
        props.onCreateCard(data)
    };


    return (
        <Modal size="lg" show={props.showModal} onHide={props.closeModal}>
            <Modal.Header closeButton>
                <Modal.Title>Create card</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form onSubmit={handleSubmit(onSubmit)}>
                    <Container>
                        <Row>
                            <Form.Group as={Col} controlId="formGridCardNumber">
                                <Form.Label>Card number</Form.Label>
                                <Controller
                                    control={control}
                                    name="cardNumber"
                                    render={
                                        ({field, fieldState}) => (
                                            <FormControl
                                                {...field}
                                                type={"text"}
                                                placeholder="Enter card number"
                                                multiline={false}
                                                helperText={fieldState.error?.message || " "}
                                                error={!!fieldState.error}
                                            />
                                        )
                                    }
                                ></Controller>
                            </Form.Group>
                        </Row>
                        <Row>
                            <Col xs={6} md={4}>
                                <FormGroup controlId="formBasicOwnerName">
                                    <FormLabel>Owner name</FormLabel>
                                    <Controller
                                        control={control}
                                        name="owner_name"
                                        render={
                                            ({field, fieldState}) => (
                                                <FormControl
                                                    {...field}
                                                    type={"text"}
                                                    placeholder="Enter owner name"
                                                    multiline={false}
                                                    helperText={fieldState.error?.message || " "}
                                                    error={!!fieldState.error}
                                                />
                                            )
                                        }
                                    ></Controller>
                                </FormGroup>
                            </Col>
                            <Col xs={6} md={4}>
                                <FormGroup controlId="formBasicOwnerSurname">
                                    <FormLabel>Owner surname</FormLabel>
                                    <Controller
                                        control={control}
                                        name="owner_surname"
                                        render={
                                            ({field, fieldState}) => (
                                                <FormControl
                                                    {...field}
                                                    type={"text"}
                                                    placeholder="Enter owner surname"
                                                    multiline={false}
                                                    helperText={fieldState.error?.message || " "}
                                                    error={!!fieldState.error}
                                                />
                                            )
                                        }
                                    ></Controller>
                                </FormGroup>
                            </Col>
                            <Col xs={6} md={4}>
                                <FormGroup controlId="formBasicBeforeDate">
                                    <FormLabel>Before date</FormLabel>
                                    <Controller
                                        control={control}
                                        name="beforeDate"
                                        render={
                                            ({field, fieldState}) => (
                                                <FormControl
                                                    {...field}
                                                    type={"text"}
                                                    placeholder="Enter before date"
                                                    multiline={false}
                                                    helperText={fieldState.error?.message || " "}
                                                    error={!!fieldState.error}
                                                />
                                            )
                                        }
                                    ></Controller>
                                </FormGroup>
                            </Col>
                        </Row>
                    </Container>

                    <Button variant="primary" type="register">
                        Create
                    </Button>
                </Form>
            </Modal.Body>
        </Modal>
    );
}

export default CreateCard;