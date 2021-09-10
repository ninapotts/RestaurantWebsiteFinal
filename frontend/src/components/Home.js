import React from 'react'
import { useHistory } from "react-router-dom";

const Home = (props) => {
    // const loggedIn = props.isLoggedIn;

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

    return (
        <div>
            <div className="input-group">
                <input type="search" className="form-control rounded" placeholder="Search for a restaurant" aria-label="Search"
                    aria-describedby="search-addon" />
                <button type="button" className="btn btn-outline-primary" onClick={search}>search</button>
            </div>
        </div>
    )
}

export default Home
