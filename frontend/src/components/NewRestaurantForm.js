import React, { useState } from 'react'
import { useHistory } from "react-router-dom";

const NewRestaurantForm = () => {

    const [rName, setRestaurantName] = useState('');
    const [rDesc, setRestaurantDesc] = useState('');
    const [rAddr, setRestaurantAddr] = useState('');

    const history = useHistory();

    const onSubmit = (event) => {
        event.preventDefault();

        addNewRestaurant();
    };

    const addNewRestaurant = () => {
        //add new restaurant to db with rating = 0 and reviews = []

        const restaurant = { restaurantName: rName, restaurantDescription: rDesc, restaurantAddress: rAddr }

        fetch("http://localhost:7060/api/restaurant", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }, body: JSON.stringify(restaurant)
        }).then(response => response.json()).catch(error => {
            console.log('Error: ', error);
        });

        history.push({ pathname: '/' });
    }


    return (
        <div style={{ margin: 20, border: '1px solid black', padding: 10 }}>
            <h4>Add a new Restaurant</h4>
            <form onSubmit={onSubmit}>
                <div className='mb-3'>
                    <label htmlFor='rName' className='form-label'>
                        Restaurant Name
                    </label>
                    <input
                        type='text'
                        id='rName'
                        className='form-control'
                        value={rName}
                        onChange={(event) => {
                            setRestaurantName(event.target.value);
                        }}
                    />
                </div>

                <div className='mb-3'>
                    <label htmlFor='rDesc' className='form-label'>
                        Restaurant Description
                    </label>
                    <input
                        type='text'
                        id='rDesc'
                        className='form-control'
                        value={rDesc}
                        onChange={(event) => {
                            setRestaurantDesc(event.target.value);
                        }}
                    />
                </div>

                <div className='mb-3'>
                    <label htmlFor='rAddress' className='form-label'>
                        Restaurant Address
                    </label>
                    <input
                        type='text'
                        id='rAddr'
                        className='form-control'
                        value={rAddr}
                        onChange={(event) => {
                            setRestaurantAddr(event.target.value);
                        }}
                    />
                </div>


                <input type='submit' className='btn btn-primary' />
            </form>
        </div>
    )
}

export default NewRestaurantForm
