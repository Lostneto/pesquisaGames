import React from 'react';
import { ReactComponent as Logo } from '../../assets/logo.svg';
import { Link } from 'react-router-dom';
import './styles.css';

const Header = () => (
    <header className="main-header">
        <Logo />
        <Link to="/">
            <div className="logo-text">
                <span className="logo-text-2"> Pesquisa</span>
                <span className="logo-text-1"> Games</span>
            </div>
        </Link>
    </header>
);

export default Header;