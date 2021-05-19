import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import 'provider.dart';

class $namePage extends StatelessWidget {
  final provider = $nameProvider();

  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (BuildContext context) => provider,
      child: Container(),
    );
  }
}