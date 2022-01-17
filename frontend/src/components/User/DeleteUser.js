import React from 'react';
import axios from 'axios';
import Footer from '../Footer';
import NavBar from '../NavBar';

class FetchUser extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            user: undefined,
            isUpdated: false
        }
    }
    componentDidMount() {
        axios.get('http://localhost:8082/user/getuserdetails/' + this.props.match.params.id)
            .then(resp => this.setState({
                user: resp.data
            }));
    }
    handleSubmit = (event) => {

        axios.delete("http://localhost:8082/user/delete/" + this.state.user.userId).then(this.setState({
            isUpdated: true
        }));
        alert("user deleted successfully")
        event.preventDefault();

    }
    render() {
        return (
            <div>
                <NavBar/>
                <h2>User Details</h2>
                {
                    this.state.user !== undefined ?
                        <div>
                            <p>User ID: {this.state.user.userId}</p>
                            <p>User Name: {this.state.user.userName}</p>
                            <p>Address: {this.state.user.address}</p>
                            <p>EmailId: {this.state.user.emailId}</p>
                            <p>Mobile: {this.state.user.mobileNo}</p>
                            <p>Are you a subscriber? {this.state.user.isSubscriber ? "Yes" : "No"}</p>
                            <p>Role: {this.state.user.role}</p>
                            <button onClick={this.handleSubmit} className='btn btn-primary'>Delete</button>
                        </div>
                        : <div>user not exists</div>
                }

                <Footer />
            </div>
        );
    }

}

export default FetchUser;