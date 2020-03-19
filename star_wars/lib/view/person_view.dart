import 'package:flutter/material.dart';
import 'package:starwars/model/person_model.dart';

class PersonView extends StatefulWidget {

  final Person person;

  PersonView(this.person);

  @override
  _PersonViewState createState() => _PersonViewState();
}

class _PersonViewState extends State<PersonView> {
  Person _person;

  @override
  void initState() {
    super.initState();
    _person = widget.person;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Personagem"),
        backgroundColor: Colors.black38,
      ),body: Padding(
      padding: EdgeInsets.only(left: 10.0),
      child: ListView(
        children: <Widget>[
          Text("Name: ${_person.name}",style: TextStyle(fontSize: 20)),
          Text("Height: ${_person.height}",style: TextStyle(fontSize: 20)),
          Text("Mass: ${_person.mass}",style: TextStyle(fontSize: 20)),
          Text("Hair_color: ${_person.hairColor}",style: TextStyle(fontSize: 20)),
          Text("Skin_color: ${_person.skinColor}",style: TextStyle(fontSize: 20)),
          Text("Eye_color: ${_person.eyeColor}",style: TextStyle(fontSize: 20)),
          Text("Birth_year: ${_person.birthYear}",style: TextStyle(fontSize: 20)),
          Text("Gender: ${_person.gender}",style: TextStyle(fontSize: 20)),
          Text("Nome do Planeta Natal: ${_person.nomePlaneta}",style: TextStyle(fontSize: 20)),
          Text("Nome da Espécie: ${_person.nomeEspecie}",style: TextStyle(fontSize: 20))
        ],
      ),
    )
    );
  }



}
