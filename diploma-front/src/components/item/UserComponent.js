import React from 'react';

function UserComponent(props) {
    return (
        <div className="item">
            <img src={"./img/" + props.user.photo} alt=""/>
            <h2>{props.user.name} {props.user.surname} {props.user.patronymic}</h2>
            <p>{props.user.email}</p>
            <b>{props.user.role}</b>
            <div className="add-to-cart" onClick={() => props.onAdd(props.user)}>+</div>
        </div>
    );
}

export default UserComponent;