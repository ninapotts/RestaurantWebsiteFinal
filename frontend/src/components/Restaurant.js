import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom';

const Restaurant = (props) => {
    const [reviews, setReviews] = useState([])

    const userType = props.location.query.userType;
    const isLoggedIn = true;


    useEffect(() => {
        fetch(`http://localhost:7060/api/review/restaurant/${props.location.query.r.id}`, {
            headers: {
                Authorization: "Bearer " + props.jwt
            }
        }).then(response => response.json()).then(data => { console.log(data); setReviews(data) })
    }, []);


    console.log("in restaurant form")
    console.log(props)
    const reviewInfo = reviews.map(r => {
        return (<li> <h5>Review by {r.user}</h5> <p><em> Comment:</em> {r.reviewContent}</p> <p> <em> Rating:</em> {r.rating}</p> </li>)
    })

    const isSignedIn = props.location.query.isSignedIn;

    if (userType === "user") {
        return (
            <div>

                {isSignedIn ? <><Link to="/LoggedIn" className="btn btn-primary">Home</Link><Link to="/" className="btn btn-primary">Logout</Link></> : <Link to="/" className="btn btn-primary">Home</Link>}

                {/* {isSignedIn ? <Link to="/" params={{ isSignedIn: false }} className="btn btn-primary">Home</Link> : <br />} */}


                <h1>{props.location.query.r.restaurantName}</h1>
                <h4>Description: </h4><p>{props.location.query.r.restaurantDescription}</p>
                <h4>Address: </h4> <p>{props.location.query.r.restaurantAddress}</p>
                <h4>Rating: </h4> <p>{props.location.query.r.restaurantRating}</p>
                <br />
                <h4>Reviews</h4>
                <ul>
                    {/* <li> <h5>Review </h5> <p><em> Comment:</em> {reviews.reviewContent}</p> <p> <em> Rating:</em> {reviews.rating}</p> </li> */}
                    {reviewInfo}

                </ul>
                <br />

                {isSignedIn ? <Link to={{ pathname: "/reviewForm", state: { restaurantId: props.location.query.r.id } }} className="btn btn-primary">Leave a Review!</Link> : <br />}


            </div>
        )
    } if (userType === "admin") {
        return (
            <div>

                <Link to="/" className="btn btn-primary">Logout</Link>

                <h1>{props.location.query.r.restaurantName}</h1>
                <h4>Description: </h4><p>{props.location.query.r.restaurantDescription}</p>
                <h4>Address: </h4> <p>{props.location.query.r.restaurantAddress}</p>
                <h4>Rating: </h4> <p>{props.location.query.r.restaurantRating}</p>
                <br />
                <h4>Reviews</h4>
                <ul>
                    <li> <h5>Review </h5> <p><em> Comment:</em> {reviews.reviewContent}</p> <p> <em> Rating:</em> {reviews.rating}</p> </li>


                </ul>
                <br />

                <button className="btn btn-danger">Edit</button>
                <Link to={{ pathname: "/editRestaurant", state: { restaurantId: props.location.query.r.id } }} className="btn btn-warning">Edit</Link>
                <Link to={{ pathname: "/deleteRestaurant", state: { restaurantId: props.location.query.r.id } }} className="btn btn-warning">Delete</Link>
            </div>
        )
    }

}

export default Restaurant