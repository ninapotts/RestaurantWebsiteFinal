import React from 'react'

const Restaurant = (props) => {
    const userType = "user";
    const isLoggedIn = true;

    // const restaurant = {
    //     "name": "McDonald's",
    //     "Description": "Shittiest food money can buy",
    //     "Rating": 0.05,
    //     "Address": "123 S Lame Avenue City, State 12345",
    //     "reviews": [
    //         {
    //             "user": "genius1",
    //             "comment": "this will not fuel my genius brain",
    //             "rating": 0
    //         },
    //         {
    //             "user": "superVillian007",
    //             "comment": "the only thing i can bring down with this food in my stomach is my plumbing",
    //             "rating": 0.1
    //         }
    //     ]
    // }
    console.log("in restaurant form")
    console.log(props.location.state)
    const reviewInfo = props.location.state.reviews.map(r => {
        return (<li> <h5>Review by {r.user}</h5> <p><em> Comment:</em> {r.comment}</p> <p> <em> Rating:</em> {r.rating}</p> </li>)
    })

    if (userType === "user") {
        return (
            <div>
                <h1>{props.location.state.name}</h1>
                <h4>Description: </h4><p>{props.location.state.Description}</p>
                <h4>Address: </h4> <p>{props.location.state.Address}</p>
                <h4>Rating: </h4> <p>{props.location.state.Rating}</p>
                <br />
                <h4>Reviews</h4>
                <ul>
                    {reviewInfo}

                </ul>
                <br />

                {isLoggedIn ? <button className="btn btn-primary">Leave a Review!</button> : <br />}

            </div>
        )
    } if (userType === "admin") {
        return (
            <div>
                <h1>{props.location.state.name} </h1>
                <h4>Description: </h4><p>{props.location.state.Description} </p>
                <h4>Address: </h4> <p>{props.location.state.Address} </p>
                <h4>Rating: </h4> <p>{props.location.state.Rating}</p>
                <ul>
                    {reviewInfo}

                </ul>

                <button className="btn btn-danger">Edit</button>
                <button className="btn btn-warning">Delete</button>
            </div>
        )
    }

}

export default Restaurant