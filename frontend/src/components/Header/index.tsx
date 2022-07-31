import logo from "../../assets/img/logo-seller.svg";
import gitImage from "../../assets/img/github-logo.svg";
import "./styles.css";

function Header() {
  return (
    <header>
      <div className="dsmeta-logo-container">
        <img src={logo} alt="SellerPride" />
        <h1>Seller Pride</h1>
        <div className="dsmeta-git-image">  
          <p>
            Desenvolvido por
            <a href="https://github.com/bob-okamura/dsmeta">
              <img src={gitImage} alt="GitImage" />/bob-okamura
            </a>
          </p>
        </div>
      </div>
    </header>
  );
}

export default Header;
