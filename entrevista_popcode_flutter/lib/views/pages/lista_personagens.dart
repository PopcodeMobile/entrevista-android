import 'package:entrevista_popcode_flutter/models/pessoa.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:getflutter/getflutter.dart';
import 'package:entrevista_popcode_flutter/helpers/helperPessoa.dart';

class ListaPersonagens extends StatelessWidget {
  final List<Pessoa> personagens;
  HelperPessoa helper = HelperPessoa();
  ListaPersonagens({Key key, this.personagens}) : super(key: key);

  void apresentarMensagem(BuildContext context) {
    final scaffold = Scaffold.of(context);
    scaffold.showSnackBar(
      SnackBar(
        content: const Text('Adicionado aos favoritos'),
        action: SnackBarAction(
            label: 'Fechar', onPressed: scaffold.hideCurrentSnackBar, textColor: Colors.green),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      padding: EdgeInsets.all(2.0),
      itemCount: personagens.length,
      itemBuilder: (context, index) {
        var pessoa = personagens[index];
        helper.save(pessoa);
        return Container(
          height: 480.0,
          child: GFCard(
            boxFit: BoxFit.cover,
            image: Image.asset('assets/images/star_wars.jpg'),
            //color: Colors.lightBlueAccent[400],
            shape:
                RoundedRectangleBorder(borderRadius: BorderRadius.circular(20)),
            title: GFListTile(
              padding: EdgeInsets.only(top: 15.0, left: 10.0, right: 10.0),
              title: Text(
                pessoa.name,
                style: TextStyle(
                    color: Colors.black, fontFamily: 'Kanit', fontSize: 20.0),
              ),
              icon: GFIconButton(
                onPressed: () => apresentarMensagem(context),
                icon: Icon(Icons.favorite, size: 25.0, color: Colors.red),
                color: Colors.white,
                size: 40.0,
                type: GFButtonType.transparent,
              ),
            ),
            content: Container(
              padding: EdgeInsets.only(left: 20.0, bottom: 20.0),
              alignment: Alignment.centerLeft,
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  Text(
                    'Altura: ' + double.parse(pessoa.height).toString(),
                    style: TextStyle(fontFamily: 'Kanit', fontSize: 17.0),
                  ),
                  Text(
                    'Peso: ' + double.parse(pessoa.mass).toString(),
                    style: TextStyle(fontFamily: 'Kanit', fontSize: 17.0),
                  ),
                  Text('Gênero: ' + pessoa.gender,
                      style: TextStyle(fontFamily: 'Kanit', fontSize: 17.0)),
                ],
              ),
            ),
            buttonBar: GFButtonBar(
              alignment: WrapAlignment.center,
              children: <Widget>[
                ButtonTheme(
                  minWidth: 100,
                  height: 40,
                  child: RaisedButton(
                    onPressed: () {},
                    child: const Text('Ver', style: TextStyle(fontSize: 15)),
                    color: Colors.amber[400],
                    shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(80)),
                    splashColor: Colors.green,
                  ),
                ),
              ],
            ),
          ),
        );
      },
    );
  }
}
