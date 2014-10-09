/*
 * SonarQube, open source software quality management tool.
 * Copyright (C) 2008-2014 SonarSource
 * mailto:contact AT sonarsource DOT com
 *
 * SonarQube is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * SonarQube is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.plugins.core.issue.tracking;

/**
 * Wrap another {@link SequenceComparator} for use with {@link HashedSequence}.
 */
public class HashedSequenceComparator<S extends Sequence> implements SequenceComparator<HashedSequence<S>> {

  private final SequenceComparator<? super S> cmp;

  public HashedSequenceComparator(SequenceComparator<? super S> cmp) {
    this.cmp = cmp;
  }

  @Override
  public boolean equals(HashedSequence<S> a, int ai, HashedSequence<S> b, int bi) {
    if (a.hashes[ai] == b.hashes[bi]) {
      return cmp.equals(a.base, ai, b.base, bi);
    }
    return false;
  }

  @Override
  public int hash(HashedSequence<S> seq, int i) {
    return seq.hashes[i];
  }

}
