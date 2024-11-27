// export default Diagnosticos;
import React from 'react';
import { Outlet } from 'react-router-dom';
import HamburgerMenu from '../HamburguerMenu/HamburguerMenu';

const Diagnosticos: React.FC = () => {
  return (
    <div>
      <HamburgerMenu buttonMarginTop="5vw"></HamburgerMenu>
      {/* Outlet renderiza as rotas internas (marca e modelo) */}
      <Outlet />
    </div>
  );
};

export default Diagnosticos;
