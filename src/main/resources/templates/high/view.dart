import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import 'provider.dart';

class $namePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (BuildContext context) => $nameProvider(),
      builder: (context, child) => _buildPage(context),
    );
  }

  Widget _buildPage(BuildContext context) {
    final provider = context.read<$nameProvider>();

    return Container();
  }
}