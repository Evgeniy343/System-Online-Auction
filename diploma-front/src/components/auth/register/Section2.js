import React, {Component} from 'react';
import {Col, Container, FormControl, FormGroup, FormLabel, Row} from "react-bootstrap";
import {Controller} from "react-hook-form";
import * as PropTypes from "prop-types";
import Select from "react-select";

function ReactSelect(props) {
    return null;
}

ReactSelect.propTypes = {
    onBlur: PropTypes.func,
    onChange: PropTypes.func,
    inputRef: PropTypes.func,
    options: PropTypes.any,
    id: PropTypes.string,
    placeholder: PropTypes.string,
    value: PropTypes.any
};

function Section2(props) {

    const optionsCountry = [
        { id: 1, title: 'BELARUS' },
        { id: 2, title: 'RUSSIA' },
        { id: 3, title: 'KAZAKHSTAN' },
    ]


        return (
            <Container>
                <Row>
                    <Col xs={6} md={4}>
                        <FormGroup controlId="formBasicCountry">
                            <FormLabel>Country</FormLabel>
                            <Controller
                                control={props.control}
                                defaultValue={""}
                                name="registrationAddress.country"
                                render={({ field }) => {
                                    console.log(field)
                                    const { onChange, value, name, ref } = field
                                    return (
                                        <Select
                                            name={name}
                                            inputRef={ref}
                                            options={optionsCountry}
                                            value={optionsCountry.find((c) => c.id === value)}
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
                        </FormGroup>
                    </Col>
                    <Col xs={6} md={4}>
                        <FormGroup controlId="formBasicRegion">
                            <FormLabel>Region</FormLabel>
                            <Controller
                                control={props.control}
                                name="registrationAddress.region"
                                render={
                                    ({field, fieldState}) => (
                                        <FormControl
                                            placeholder="Enter region"
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
                        <FormGroup controlId="formBasicCity">
                            <FormLabel>City</FormLabel>
                            <Controller
                                control={props.control}
                                name="registrationAddress.city"
                                render={
                                    ({field, fieldState}) => (
                                        <FormControl
                                            placeholder="Enter city"
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

                <Row>
                    <Col xs={6} md={4}>
                        <FormGroup controlId="formBasicStreet">
                            <FormLabel>Street</FormLabel>
                            <Controller
                                control={props.control}
                                name="registrationAddress.street"
                                render={
                                    ({field, fieldState}) => (
                                        <FormControl
                                            placeholder="Enter street"
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
                        <FormGroup controlId="formBasicHouse">
                            <FormLabel>House</FormLabel>
                            <Controller
                                control={props.control}
                                name="registrationAddress.house"
                                render={
                                    ({field, fieldState}) => (
                                        <FormControl
                                            placeholder="Enter house"
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
                        <FormGroup controlId="formBasicFlat">
                            <FormLabel>Flat</FormLabel>
                            <Controller
                                control={props.control}
                                name="registrationAddress.flat"
                                render={
                                    ({field, fieldState}) => (
                                        <FormControl
                                            placeholder="Enter flat"
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

export default Section2;