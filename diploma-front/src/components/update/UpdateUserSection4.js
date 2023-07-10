import React from 'react';
import {Col, Container, Form, FormControl, FormGroup, FormLabel, Row} from "react-bootstrap";
import {Controller} from "react-hook-form";
import Select from "react-select";

function UpdateUserSection4(props) {
    const optionsDocument = [
        { id: 1, title: 'PASSPORT' },
        { id: 2, title: 'RESIDENCE PERMIT' },
        { id: 3, title: 'REFUGEE CERTIFICATE' },
    ]

    return (
        <Container>
            <Row>
                <Form.Group as={Col} controlId="formGridIdentityDocument">
                    <Form.Label>Identity document</Form.Label>
                    <Controller
                        control={props.control}
                        defaultValue={""}
                        name="identification.identityDocument"
                        render={({ field }) => {
                            console.log(field)
                            const { onChange, value, name, ref } = field
                            return (
                                <Select
                                    name={name}
                                    inputRef={ref}
                                    options={optionsDocument}
                                    value={optionsDocument.find((c) => c.id === value)}
                                    onChange={(val) => {
                                        onChange(val)
                                    }}
                                    getOptionValue={(option) => option.id}
                                    getOptionLabel={(option) => option.title}
                                    className="basic-multi-select"
                                    classNamePrefix="select"
                                />
                            )
                        }}
                    />
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

export default UpdateUserSection4;