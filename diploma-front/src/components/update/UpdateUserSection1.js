import React from 'react';
import {Col, Container, FormControl, FormGroup, FormLabel, Row} from "react-bootstrap";
import {Controller} from "react-hook-form";

function UpdateUserSection1(props) {
    return (
        <Container>
            <Row>
                <Col xs={6} md={4}>
                    <FormGroup controlId="formBasicName">
                        <FormLabel>Name</FormLabel>
                        <Controller
                            control={props.control}
                            name="name"
                            render={
                                ({field, fieldState}) => (
                                    <FormControl
                                        placeholder="Enter name"
                                        {...field}
                                        type={"text"}
                                        label='name'
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
                    <FormGroup controlId="formBasicSurname">
                        <FormLabel>Surname</FormLabel>
                        <Controller
                            control={props.control}
                            name="surname"
                            render={
                                ({field, fieldState}) => (
                                    <FormControl
                                        placeholder="Enter surname"
                                        {...field}
                                        type={"surname"}
                                        label='name'
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
                    <FormGroup controlId="formBasicPatronymic">
                        <FormLabel>Patronymic</FormLabel>
                        <Controller
                            control={props.control}
                            name="patronymic"
                            render={
                                ({field, fieldState}) => (
                                    <FormControl
                                        placeholder="Enter patronymic"
                                        {...field}
                                        type={"text"}
                                        label='patronymic'
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
    );
}

export default UpdateUserSection1;