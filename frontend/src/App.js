import Restaurant from './components/Restaurant';
// import SigninPage from './components/SigninPage';
import { Switch, Route } from 'react-router-dom';
import NewUserForm from './components/NewUserForm';
import NewRestaurantForm from './components/NewRestaurantForm';
import Home from './components/Home';
import Login from './components/Login';
import LoggedIn from './components/LoggedIn';
import React, { useState } from 'react'
import ReviewForm from './components/ReviewForm';
import { DeleteRestaurant } from './components/DeleteRestaurant';
import EditRestaurant from './components/EditRestaurant';



function App() {
  const [jwt, setJwt] = useState("");


  return (
    <main>
      <Switch>
        <Route path='/' render={() => <Home jwt={jwt} userType="user" />} exact />
        <Route path='/loggedIn' component={LoggedIn} />
        <Route path='/login' render={() => <Login setJwt={setJwt} />} />
        <Route path='/restaurant' component={Restaurant} />
        <Route path='/newUser' render={() => <NewUserForm setJwt={setJwt} />} />
        <Route path='/addNewRestaurant' component={NewRestaurantForm} />
        <Route path='/reviewForm' component={ReviewForm} />
        <Route path='/deleteRestaurant' component={DeleteRestaurant} />
        <Route path='/editRestaurant' component={EditRestaurant} />

      </Switch>
    </main>
  );
}

export default App;
