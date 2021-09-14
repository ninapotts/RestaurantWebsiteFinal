import React, { useState } from 'react'
import { useHistory } from "react-router-dom";

const ReviewForm = (props) => {
    const [rating, setRating] = useState('');
    const [reviewContent, setReviewContent] = useState('');

    console.log("in review forms")
    console.log(props)

    const history = useHistory();

    const onSubmit = (event) => {
        event.preventDefault();

        const review = { "id": 1, "reviewContent": reviewContent, "rating": parseInt(rating), "restaurant": { "id": props.location.state.restaurantId } }

        fetch("http://localhost:7060/api/review/", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                // Authorization: "Bearer " + props.jwt
            }, body: JSON.stringify(review)
        }).then(response => response.json()).catch(error => {
            console.log('Error: ', error);
        });

        history.push({ pathname: '/' });
    };



    return (
        <div style={{ margin: 20, border: '1px solid black', padding: 10 }}>
            <h4>Review Form</h4>
            <form onSubmit={onSubmit}>
                <div className='mb-3'>
                    <label htmlFor='reviewContent' className='form-label'>
                        Review:
                    </label>
                    <input
                        type='text'
                        id='reviewContent'
                        className='form-control'
                        value={reviewContent}
                        onChange={(event) => {
                            setReviewContent(event.target.value);
                        }}
                    />
                </div>

                <div className='mb-3'>
                    <label
                        htmlFor='rating'
                        className='form-label'
                        style={{ display: 'block' }}>
                        Rating:
                    </label>
                    <input type="number" id="rating" className='form-control' value={rating}
                        onChange={(event) => {
                            setRating(event.target.value);
                        }} />
                </div>



                <input type='submit' className='btn btn-primary' />
            </form>
        </div>
    )
}

export default ReviewForm
