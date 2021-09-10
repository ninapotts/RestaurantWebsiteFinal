import Restaurant from './components/Restaurant';
import SigninPage from './components/SigninPage';
import { Switch, Route } from 'react-router-dom';
import LogInForm from './components/LogInForm';
import NewRestaurantForm from './components/NewRestaurantForm';



function App() {
  return (
    <main>
      <Switch>
        <Route path='/' component={SigninPage} exact />
        <Route path='/restaurant' component={Restaurant} />
        <Route path='/logIn' component={LogInForm} />
        <Route path='/addNewRestaurant' component={NewRestaurantForm} />
      </Switch>
    </main>
  );
}

export default App;
