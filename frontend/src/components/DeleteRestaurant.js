import React, { useEffect } from 'react'
import Home from './Home';

export const DeleteRestaurant = (props) => {




    useEffect(() => {
        console.log("in delete")
        console.log(props);
        fetch(`http://localhost:7060/api/restaurant/${props.location.state.restaurantId}`, {
            method: "Delete",
            headers: {
                Authorization: "Bearer " + props.jwt
            }

        }).then(
        ).then(response => response.json()).then(data => { console.log(data); })
    }, []);


    return (
        <div>
            <Home jwt={props.jwt} userType="admin" isSignedIn={true} />
        </div>
    )
}
