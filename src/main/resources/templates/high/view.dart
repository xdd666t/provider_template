import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import 'provider.dart';

class $namePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (BuildContext context) => $nameProvider(),
      child: Container(),
    );
  }
}