import React from 'react'
import { useHistory, Link } from "react-router-dom";
import Home from './Home';

const LoggedIn = (props) => {

    const history = useHistory();

    const search = () => {
        history.push({
            pathname: '/restaurant', state: {
                "name": "McDonald's",
                "Description": "Shittiest food money can buy",
                "Rating": 0.05,
                "Address": "123 S Lame Avenue City, State 12345",
                "reviews": [
                    {
                        "user": "genius1",
                        "comment": "this will not fuel my genius brain",
                        "rating": 0
                    },
                    {
                        "user": "superVillian007",
                        "comment": "the only thing i can bring down with this food in my stomach is my plumbing",
                        "rating": 0.1
                    }
                ]
            }
        });
    }

    const loggedInView = () => {
        const name = props.location.state.user.username;

        console.log(name)
        if (props.location.state.user.username == "world") {
            return <Home isSignedIn={true} userType="admin" />

        } else {
            return <Home isSignedIn={true} userType="user" />
        }
    }


    return (
        <div>
            {console.log(props)}
            <Link to='/' className="btn btn-outline-primary">Log Out</Link>
            {loggedInView()}

        </div>
    )
}

export default LoggedIn
