import styled from "styled-components";

export const Espacadores = styled.div`
@media screen and (min-width: 320px) and (max-width: 740px) {
    .espacadorEsquerdo{
         background-color: black;
        height: 0.5vw;
        width: 30%;
        border-radius: 1vw;
        margin-left: 10%;
        margin-top: -23vw;
      }
    .espacadorDireito{
        background-color: black;
        height: 0.5vw;
        width: 30%;
        border-radius: 1vw;
        margin-left: 59%;
        margin-top: -0.5vw;
      }
}
@media screen and (min-width: 741px) and (max-width: 1280px) {
    .espacadorEsquerdo{
         background-color: black;
        height: 0.5vw;
        width: 30%;
        border-radius: 1vw;
        margin-left: 10%;
        margin-top: -23vw;
      }
    .espacadorDireito{
        background-color: black;
        height: 0.5vw;
        width: 30%;
        border-radius: 1vw;
        margin-left: 59%;
        margin-top: -0.5vw;
      }
}
@media (min-width: 1281px) {
    .espacadorEsquerdo{
         background-color: black;
        height: 0.5vw;
        width: 30%;
        border-radius: 1vw;
        margin-left: 10%;
        margin-top: -23vw;
      }
    .espacadorDireito{
        background-color: black;
        height: 0.5vw;
        width: 30%;
        border-radius: 1vw;
        margin-left: 59%;
        margin-top: -0.5vw;
      }
}
     
`
export const Radio = styled.input.attrs({ type: 'radio' })`
@media screen and (min-width: 320px) and (max-width: 740px) {
    width: 3vw;
    height: 3vw;
    margin: 0.5vw;
    appearance: none;
    border: 0.1vw solid gray;
    border-radius: 50%;
    position: relative;
    left: 37.3vw;
    bottom: 85vw;
    &:checked {
        background-color: #007bff; 
        border-color: #007bff;
    }
}
@media screen and (min-width: 741px) and (max-width: 1280px) {
    width: 3vw;
    height: 3vw;
    margin: 0.5vw;
    appearance: none;
    border: 0.1vw solid gray;
    border-radius: 50%;
    position: relative;
    left: 37.3vw;
    bottom: 85vw;
    &:checked {
        background-color: #007bff; 
        border-color: #007bff;
    }
}
@media (min-width: 1281px) {
    width: 3vw;
    height: 3vw;
    margin: 0.5vw;
    appearance: none;
    border: 0.1vw solid gray;
    border-radius: 50%;
    position: relative;
    left: 37.3vw;
    bottom: 85vw;
    &:checked {
        background-color: #007bff; 
        border-color: #007bff;
    }
}
`

