import React from 'react';
import {Col, Container, Form, FormControl, FormGroup, FormLabel, Row} from "react-bootstrap";
import {Controller} from "react-hook-form";
import Select from "react-select";

function Section4(props) {

    const selectOptions = [
        {value: "PASSPORT", label: "PASSPORT"},
        {value: "RESIDENCE PERMIT", label: "RESIDENCE PERMIT"},
        {value: "REFUGEE CERTIFICATE", label: "REFUGEE CERTIFICATE"}
    ];

    return (
        <Container>
            <Row>
                <Form.Group as={Col} controlId="formGridIdentityDocument">
                    <Form.Label>Identity document</Form.Label>
                    <Controller
                        control={props.control}
                        name="identification.identityDocument"
                        render={
                            ({field, fieldState}) => (
                                <FormControl
                                    {...field}
                                    type={"text"}
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
                    <FormGroup controlId="formBasicSeriesAndDocumentNumber">
                        <FormLabel>Series and document number</FormLabel>
                        <Controller
                            control={props.control}
                            name="identification.seriesAndDocumentNumber"
                            render={
                                ({field, fieldState}) => (
                                    <FormControl
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
                </Col>
                <Col xs={6} md={4}>
                    <FormGroup controlId="formBasicWhoIssuedDocument">
                        <FormLabel>Who issued document</FormLabel>
                        <Controller
                            control={props.control}
                            name="identification.whoIssuedDocument"
                            render={
                                ({field, fieldState}) => (
                                    <FormControl
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
                </Col>
                <Col xs={6} md={4}>
                    <FormGroup controlId="formBasicWhenDocumentWasIssued">
                        <FormLabel>When document was issued</FormLabel>
                        <Controller
                            control={props.control}
                            name="identification.whenDocumentWasIssued"
                            render={
                                ({field, fieldState}) => (
                                    <FormControl
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
                </Col>
            </Row>
        </Container>
    );
}

export default Section4;