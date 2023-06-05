import React, {useEffect, useState} from "react"
import "bootstrap/dist/css/bootstrap.min.css"
import UserService from "../../service/UserService"
import UserComponent from "../item/UserComponent";

const ListUserComponent = (props) => {
    const [users, setUsers] = useState([])

    useEffect(() => {
        getAllUsers()
    }, [])

    const getAllUsers = () => {
        UserService.getUsers().then((response) => {
            setUsers(response.data)
            console.log(response.data)
        }).catch(error => {
            console.log(error)
        })
    }

    // const deleteUserById = (userId) => {
    //     UserService.deleteUserById(userId).then((response) => {
    //         getAllUsers()
    //     }).catch(error => {
    //         console.log(error)
    //     })
    // }

    return (
        <main>
            {users.map(user => (
                    <UserComponent key={user.id} user={user} onAdd={props.onAdd}/>
                )
            )}
        </main>
    )

    // return (
    //     <div className="component_color">
    //         <div className="container">
    //             <h2 className="text-center">List Users</h2>
    //             <Link to="/users/create" className="btn btn-primary mb-2">Add user</Link>
    //             <table className="table table-bordered table-striped">
    //                 <thead>
    //                 <th>Id</th>
    //                 <th>Name</th>
    //                 <th>Surname</th>
    //                 <th>Email</th>
    //                 <th>Password</th>
    //                 <th>Phone number</th>
    //                 <th>Role</th>
    //                 <th>Photo</th>
    //                 <th>Actions</th>
    //                 </thead>
    //                 <tbody>{
    //                     users.map(
    //                         user =>
    //                             <tr key={user.id}>
    //                                 <td>{user.id}</td>
    //                                 <td>{user.name}</td>
    //                                 <td>{user.surname}</td>
    //                                 <td>{user.email}</td>
    //                                 <td>{user.password}</td>
    //                                 <td>{user.phoneNumber}</td>
    //                                 <td>{user.role}</td>
    //                                 <td>{user.photo}</td>
    //                                 <td>
    //                                     <Link className="btn btn-info" to={`/users/update/${user.id}`}>Update</Link>
    //                                     <button className="btn btn-danger" onClick={() => deleteUserById(user.id)}
    //                                             style={{marginLeft: "10px"}}>
    //                                         Delete
    //                                     </button>
    //                                 </td>
    //                             </tr>
    //                     )
    //                 }
    //                 </tbody>
    //             </table>
    //         </div>
    //     </div>
    // )
}

export default ListUserComponent