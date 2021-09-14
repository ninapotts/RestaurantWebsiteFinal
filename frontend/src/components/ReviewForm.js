import React, { useState } from 'react'
import { useHistory } from "react-router-dom";

const ReviewForm = (props) => {
    const [rating, setRating] = useState('');
    const [reviewContent, setReviewContent] = useState('');



    const history = useHistory();

    const onSubmit = (event) => {
        event.preventDefault();

        const review = { reviewContent: reviewContent, rating: rating, restaurant_id: props.restaurantId }

        fetch("http://localhost:7060/api/review", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                Authorization: "Bearer " + props.jwt
            }, body: JSON.stringify(review)
        }).then(response => response.json()).then(data => { setRating(data.rating); setReviewContent(data.reviewContent); props.setJwt(data.jwt) }).catch(error => {
            console.log('Error: ', error);
        });

        history.push({ pathname: '/', state: { jwt: props.jwt } });
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
