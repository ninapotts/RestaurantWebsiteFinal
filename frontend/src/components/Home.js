import React, { useEffect, useState } from 'react'
import { useHistory, Link } from "react-router-dom";

const Home = (props) => {
    // const loggedIn = props.isLoggedIn;
    const [restaurants, setRestaurants] = useState([]);
    const [searchRestaurant, setSearch] = useState('');
    const [r, setR] = useState({});

    const history = useHistory();

    console.log(props)


    const search = () => {

        return fetch(`http://localhost:7060/api/restaurant/name/${searchRestaurant}`).then(response => response.json()).then(data => setR(data))
    }

    async function asyncCall() {
        const result = await search();
        console.log(result)
        return result
    }

    function refreshPage() {
        window.location.reload(false);
    }


    useEffect(() => {
        fetch("http://localhost:7060/api/restaurant", {
            headers: {
                Authorization: "Bearer " + props.jwt
            }

        }).then(
        ).then(response => response.json()).then(data => { console.log(data); setRestaurants(data) })
    }, []);



    const resList = restaurants.map(r => {
        return (<Link className="btn btn-warning" style={{ padding: "10px", margin: "10px" }} to={{ pathname: "/restaurant", query: { r, isSignedIn: props.isSignedIn, userType: props.userType } }} key={r.id}>{r.restaurantName} </Link>)
    })

    const addNew = () => {
        if (props.userType === "admin") {
            return <Link to="/addNewRestaurant" className="btn btn-danger" > Add New</Link>
        }
    }



    return (
        <div>

            {props.isSignedIn ? <br /> : <><Link className="btn btn-primary" to="/newUser">Create Account</Link><Link className="btn btn-primary" to="/login">Login</Link></>}
            {refreshPage}
            <div className="input-group">
                <input type="search" className="form-control rounded" placeholder="Search for a restaurant" aria-label="Search"
                    aria-describedby="search-addon" onChange={(event) => {
                        setSearch(event.target.value); console.log(event.target.value); asyncCall();
                    }} />
                <Link to={{ pathname: "/restaurant", query: { r: r, isSignedIn: props.isSignedIn, userType: props.userType } }} > search</Link>
            </div>

            {addNew()}
            <ul>
                {resList}
            </ul>


        </div>
    )
}

export default Home
