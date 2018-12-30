/**
 *
 * App
 *
 * This component is the skeleton around the actual pages, and should only
 * contain code that should be seen on all pages. (e.g. navigation bar)
 */

import React from 'react';
import { Helmet } from 'react-helmet';
import StockMap from 'containers/StockMap';
import ChatSite from 'containers/ChatSite';
import SearchSite from 'containers/SearchSite';
import Logo from 'components/Header/Logo';
import './Style.css';

export default function App() {
  return (
    <div>
      <Helmet titleTemplate="%s - RelStock" defaultTitle="RelStock">
        <meta name="description" content="It is a relation stock analytics" />
      </Helmet>
      <SearchSite />
      <ChatSite />
      <Logo />
      <StockMap />
    </div>
  );
}
